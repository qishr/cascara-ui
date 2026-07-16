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


package io.github.qishr.cascara.ui.schema;

import java.util.HashMap;
import java.util.Map;

import io.github.qishr.cascara.schema.util.Schemas;
import io.github.qishr.cascara.schema.util.SchemaResolver;
import io.github.qishr.cascara.schema.util.TypeAnalyzer;

public class TypeAnalyzers {
    private static TypeAnalyzers instance;
    private SchemaResolver schemaResolver = Schemas.getResolver();
    private Map<String, TypeAnalyzer> analyzers = new HashMap<>();

    private TypeAnalyzers() {}

    public static TypeAnalyzers instance() {
        if (instance == null) {
            instance = new TypeAnalyzers();
        }
        return instance;
    }

    public static Map<String, TypeAnalyzer> getAnalyzers() { return instance().analyzers; }

    public static void register(TypeAnalyzer analyazer) {
        instance().analyzers.put(analyazer.getClass().getName(), analyazer);
    }

    public static boolean isRegistered(Class<? extends TypeAnalyzer> clazz) {
        return instance().analyzers.keySet().contains(clazz.getName());
    }

    public static SchemaResolver getSchemaResolver() { return instance().schemaResolver; }

    public static void setSchemaResolver(SchemaResolver resolver) {
        instance().schemaResolver = resolver;
    }



}
