package in.twbs.pure.lang.highlight;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import in.twbs.pure.lang.lexer.PureLexer;
import in.twbs.pure.lang.psi.PureTokens;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class PureSyntaxHighlighter extends SyntaxHighlighterBase implements PureTokens {
    private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();

    public static final TextAttributesKey LINE_COMMENT = createKey("pure.LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);

    public static final TextAttributesKey BLOCK_COMMENT = createKey("pure.BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);

    public static final TextAttributesKey KEYWORD = createKey("pure.KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);

    public static final TextAttributesKey STRING = createKey("pure.STRING", DefaultLanguageHighlighterColors.STRING);

    public static final TextAttributesKey NUMBER = createKey("pure.NUMBER", DefaultLanguageHighlighterColors.NUMBER);

    public static final TextAttributesKey BRACKET = createKey("pure.BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);

    public static final TextAttributesKey OPERATOR = createKey("pure.OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);

    public static final TextAttributesKey IDENTIFIER = createKey("pure.IDENTIFIER", CodeInsightColors.LOCAL_VARIABLE_ATTRIBUTES);

    public static final TextAttributesKey TYPE_NAME = createKey("pure.TYPE_NAME", CodeInsightColors.ANNOTATION_NAME_ATTRIBUTES);

    public static final TextAttributesKey VARIABLE = createKey("pure.VARIABLE", CodeInsightColors.INSTANCE_FIELD_ATTRIBUTES);

    public static final TextAttributesKey CONST = createKey("pure.CONST", CodeInsightColors.STATIC_FINAL_FIELD_ATTRIBUTES);

    public static final TextAttributesKey GLOBAL_VARIABLE = createKey("pure.GLOBAL_VARIABLE", CodeInsightColors.STATIC_FIELD_ATTRIBUTES);

    public static final TextAttributesKey METHOD_DECLARATION = createKey("pure.METHOD_DECLARATION", CodeInsightColors.METHOD_DECLARATION_ATTRIBUTES);

    static {
        fillMap(ATTRIBUTES, TokenSet.create(SLCOMMENT), LINE_COMMENT);
        fillMap(ATTRIBUTES, TokenSet.create(MLCOMMENT), BLOCK_COMMENT);
        fillMap(ATTRIBUTES, kKeywords, KEYWORD);
        fillMap(ATTRIBUTES, TokenSet.create(NATURAL), NUMBER);
        fillMap(ATTRIBUTES, kStrings, STRING);
        fillMap(ATTRIBUTES, TokenSet.create(LPAREN, RPAREN), BRACKET);
        fillMap(ATTRIBUTES, TokenSet.create(LBRACK, RBRACK), BRACKET);
        fillMap(ATTRIBUTES, TokenSet.create(LCURLY, RCURLY), BRACKET);
        fillMap(ATTRIBUTES, kOperators, OPERATOR);
        fillMap(ATTRIBUTES, TokenSet.create(IDENT), IDENTIFIER);
        fillMap(ATTRIBUTES, TokenSet.create(ERROR), CodeInsightColors.ERRORS_ATTRIBUTES);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new PureLexer();
    }

    @NotNull
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(ATTRIBUTES.get(tokenType));
    }

    private static TextAttributesKey createKey(String externalName, TextAttributesKey fallbackAttrs) {
        return createTextAttributesKey(externalName, fallbackAttrs);
    }
}
