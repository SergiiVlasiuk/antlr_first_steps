package org.antlr.eecs4302.expression;

import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.*;

import java.util.Collections;
import java.util.List;

@Slf4j
public class ExprErrorListener extends BaseErrorListener {

    public static boolean hasError = false;

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg,
                            RecognitionException e) {
        hasError = true;
        List<String> stack = ((Parser) recognizer).getRuleInvocationStack();
        Collections.reverse(stack);
        log.error("Syntax Error!");
        log.error("Token \"%s\" (line %d, column %d): %s"
                .formatted(((Token)offendingSymbol).getText(), line, charPositionInLine + 1, msg));
        log.error("Rule stack: {}", stack);
//
//        super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
    }
}
