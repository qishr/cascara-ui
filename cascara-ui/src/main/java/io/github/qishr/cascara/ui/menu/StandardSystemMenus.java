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


package io.github.qishr.cascara.ui.menu;

import io.github.qishr.cascara.common.property.Properties;

import javafx.stage.Stage;

public class StandardSystemMenus implements SystemMenusService {
    private Properties properties;
    private ObservableMenuItem menuRoot;
    private Runnable onAbout = null;
    private Runnable onSettings = null;
    private Runnable onQuit = null;

    public StandardSystemMenus() {
    }

    @Override
    public Properties getServiceProperties() {
        if (properties == null) {
            properties = new Properties();
        }
        return properties;
    }

    @Override
    public void setOnAbout(Runnable handler) {this.onAbout = handler;}

    public void onAbout() {if (onAbout != null) {onAbout.run();}}

    @Override
    public void setOnSettings(Runnable handler) {this.onSettings = handler;}

    public void onSettings() {if (onSettings != null) {onSettings.run();}}

    @Override
    public void setOnQuit(Runnable handler) {this.onQuit = handler;}

    public void onQuit() {if (onQuit != null) {onQuit.run();}}

    @Override
    public void setMenuRoot(ObservableMenuItem menuRoot) {
        this.menuRoot = menuRoot;
    }

    @Override
    public void integrate(Stage stage) {
    }

    @Override
    public ObservableMenuItem buildAppMenu(String appName) {
        ObservableMenuItem appMenu = menuRoot.addMenu("app", "AppMenu");
        return appMenu;
    }

    @Override
    public ObservableMenuItem buildWindowMenu() {
        ObservableMenuItem windowMenu = menuRoot.addMenu("window", "Window");
        // Menu windowMenu = new Menu("WindowMenu");
        return windowMenu;
    }
}
