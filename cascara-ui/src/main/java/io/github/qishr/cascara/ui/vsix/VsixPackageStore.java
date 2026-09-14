// # License & Terms
//
// This file is part of **Cascara**.
//
// **Cascara** is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program. If not, see <https://www.gnu.org/licenses/>.
//
// ---
//
// ## Special Runtime Exception
//
// As a special exception, the copyright holders of this library give you
// permission to link this library with independent modules to produce an
// executable, regardless of the license terms of these independent modules,
// and to copy and distribute the resulting executable under terms of your
// choice, provided that you also meet, for each linked independent module,
// the terms and conditions of the license of that module.
//
// An independent module is a module which is not derived from or based on
// this library. If you modify this library, you may extend this exception
// to your version of the library, but you are not obligated to do so. If
// you do not wish to do so, delete this exception statement from your
// version.


package io.github.qishr.cascara.ui.vsix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import io.github.qishr.cascara.common.diagnostic.LocalizableIOException;
import io.github.qishr.cascara.common.diagnostic.code.GenericDiagnosticCode;
import io.github.qishr.cascara.common.io.filewatcher.FileWatcher;
import io.github.qishr.cascara.common.io.filewatcher.FileChangeHandler;
import io.github.qishr.cascara.common.io.filewatcher.FileChangeType;
import io.github.qishr.cascara.common.property.Properties;
import io.github.qishr.cascara.format.vsix.VsixPackage;
// import io.github.qishr.cascara.ui.vsix.VsixPackageInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VsixPackageStore implements AutoCloseable {
    private static final Path cascaraDir = Paths.get(System.getProperty("user.home")).resolve(".cascara");
    private static final Path packageDir = cascaraDir.resolve("packages");

    private final ObservableList<VsixPackageInfo> packagesList = FXCollections.observableArrayList();
    private FileWatcher fileWatcher;
    private static VsixPackageStore instance;

    // private Reporter reporter = new NoOpReporter();

    private VsixPackageStore() {
        init();
    }

    @Override
    public void close() {
        if (instance != null) {
            fileWatcher.clear();
            instance = null;
        }
    }

    private void init() {
        enumeratePackages();
        // TODO: File watcher
        try {
			fileWatcher = new FileWatcher();
            fileWatcher.watchDirectory(packageDir, new FileChangeHandler() {

				@Override
				public void handle(FileChangeType type, Path path) {
                    if (type == FileChangeType.CREATED) {
                        VsixPackageInfo info = readPackageInfo(path);
                        packagesList.add(info);
                    }
                    if (type == FileChangeType.DELETED) {
                        for (VsixPackageInfo info : packagesList) {
                            if (info.getPath().equals(path)) {
                                packagesList.remove(info);
                                return;
                            }
                        }
                    }
				}
            });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static VsixPackageStore instance() {
        if (instance == null) {
            instance = new VsixPackageStore();
        }
        return instance;
    }

    // public void setReporter(Reporter reporter) {
    //     if (reporter == null) {
    //         this.reporter = new NoOpReporter();
    //     } else {
    //         this.reporter = reporter;
    //     }
    // }

    public ObservableList<VsixPackageInfo> getPackages() {
        // enumeratePackages();
        return packagesList;
    }

    private void enumeratePackages() {
        List<VsixPackageInfo> list = new ArrayList<>();
        if (Files.isDirectory(packageDir)) {
            try {
                Files.list(packageDir).forEach(path -> {
                    VsixPackageInfo info = readPackageInfo(path);
                    if (info != null) {
                        if (info.getPath() == null) {
                            System.out.println("Debug");
                        }
                        list.add(info);
                    }
                });
                packagesList.retainAll(list);
                packagesList.setAll(list);
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    private VsixPackageInfo readPackageInfo(Path path) {
        try {
            VsixPackage pkg = VsixPackage.open(path);
            // VsixMetadata manifest = pkg.getMetadata();

            String name = pkg.getName();
            String displayName = pkg.getDisplayName();

            if (name == null || displayName == null) {
                return null;
            }
            VsixPackageInfo info = new VsixPackageInfo(path, name, displayName);
            return info;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public void install(Path packagePath) throws LocalizableIOException {
        Path fileName = packagePath.getFileName();
        try {
            Files.copy(packagePath, packageDir.resolve(fileName));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new LocalizableIOException(e, GenericDiagnosticCode.IO_ERROR, e.getMessage());
        }
    }
}
