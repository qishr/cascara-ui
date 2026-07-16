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


package io.github.qishr.cascara.ui.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.github.qishr.cascara.common.lang.token.Token;

public class HighlightingToken {
    private static Map<HighlightingToken.Kind,String> kindToString = new HashMap<>();
    private static Map<String,HighlightingToken.Kind> stringToKind = new HashMap<>();

    private Kind kind = Kind.OTHER;
    private String lexeme = null;
    private int startIndex = -1;
    private int line = -1;
    private int column = -1;

    public enum Kind {
        // Semantic
        ANNOTATION,
        CONSTANT,
        METHOD,
        NAMESPACE,
        PRIMITIVE_TYPE,
        REFERENCE_TYPE,
        REGEX,
        VARIABLE,
        MODIFIER,

        // Syntatic
        CHARACTER,
        COMMENT,
        IDENTIFIER,
        KEYWORD,
        LITERAL,
        NUMBER,
        OPERATOR,
        OTHER,
        PUNCTUATION,
        STRING;

    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public static Kind getKindForToken(Token token) {
        return switch (token.getType().getCategory()) {
            case KEYWORD      -> Kind.KEYWORD;
            case STRING       -> Kind.STRING;
            case NUMBER       -> Kind.NUMBER;
            case BOOLEAN      -> Kind.NUMBER;
            case NULL         -> Kind.NUMBER;
            case COMMENT      -> Kind.COMMENT;
            case DOC_COMMENT  -> Kind.COMMENT;
            case TYPE_NAME    -> Kind.REFERENCE_TYPE;
            case FUNCTION_NAME-> Kind.METHOD;
            case FIELD_NAME,
                 PARAMETER_NAME,
                 IDENTIFIER   -> Kind.VARIABLE;
            case OPERATOR     -> Kind.OPERATOR;
            case PUNCTUATION,
                 SYMBOL,
                 DELIMITER    -> Kind.PUNCTUATION;
            case TEXT         -> Kind.OTHER;
            case META         -> Kind.OPERATOR;
            case STRUCTURAL   -> Kind.PUNCTUATION;
            default -> Kind.OTHER;
        };
    }

    public static void init() {
        kindToString.put(HighlightingToken.Kind.ANNOTATION, "annotation");
        kindToString.put(HighlightingToken.Kind.CONSTANT, "constant");
        kindToString.put(HighlightingToken.Kind.METHOD, "method");
        kindToString.put(HighlightingToken.Kind.MODIFIER, "modifier");
        kindToString.put(HighlightingToken.Kind.NAMESPACE, "namespace");
        kindToString.put(HighlightingToken.Kind.PRIMITIVE_TYPE, "primitive_type");
        kindToString.put(HighlightingToken.Kind.REFERENCE_TYPE, "reference_type");
        kindToString.put(HighlightingToken.Kind.REGEX, "regex");
        kindToString.put(HighlightingToken.Kind.VARIABLE, "variable");

        kindToString.put(HighlightingToken.Kind.CHARACTER, "character");
        kindToString.put(HighlightingToken.Kind.COMMENT, "comment");
        kindToString.put(HighlightingToken.Kind.IDENTIFIER, "variable");
        kindToString.put(HighlightingToken.Kind.KEYWORD, "keyword");
        kindToString.put(HighlightingToken.Kind.LITERAL, "literal");
        kindToString.put(HighlightingToken.Kind.NUMBER, "number");
        kindToString.put(HighlightingToken.Kind.OPERATOR, "operator");
        kindToString.put(HighlightingToken.Kind.OTHER, "other");
        kindToString.put(HighlightingToken.Kind.PUNCTUATION, "punctuation");
        kindToString.put(HighlightingToken.Kind.STRING, "string");

        for (HighlightingToken.Kind kind : kindToString.keySet()) {
            stringToKind.put(kindToString.get(kind), kind);
        }
    }

    public static Set<HighlightingToken.Kind> getKinds() {
        if (kindToString.isEmpty()) {
            init();
        }
        return kindToString.keySet();
    }

    public static HighlightingToken.Kind getKind(String name) {
        HighlightingToken.Kind  kind = stringToKind.get(name);
        if (kind == null) {
            return HighlightingToken.Kind.OTHER;
        } else {
            return kind;
        }
    }

    public static String getString(HighlightingToken.Kind kind) {
        String string = kindToString.get(kind);
        if (kind == null) {
            return "other";
        } else {
            return string;
        }
    }
}
