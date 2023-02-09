package org.michalbaran.components;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Symbol {
    EMP("   ", AnsiColor.RESET),
    SYR("SYR", AnsiColor.YELLOW),
    HED("HED", AnsiColor.RED),
    GRY("GRY", AnsiColor.BLUE),
    ZGR("ZGR", AnsiColor.BRIGHT_BLUE),
    TRE("TRE", AnsiColor.YELLOW),
    MCG("MCG", AnsiColor.GREEN),
    RON("RON", AnsiColor.BRIGHT_GREEN),
    DRA("DRA", AnsiColor.BRIGHT_GREEN),
    HAR("HAR", AnsiColor.BRIGHT_RED),
    TON("TON", AnsiColor.GREEN),
    HAG("HAG", AnsiColor.BRIGHT_YELLOW),
    HER("HER", AnsiColor.WHITE),
    DUM("DUM", AnsiColor.CYAN),
    LUN("LUN", AnsiColor.BRIGHT_MAGENTA),
    FLE("FLE", AnsiColor.BRIGHT_CYAN);

    private final String text;
    private final AnsiColor color;

    @Override
    public String toString() {
        return color.getColorCode() + text + AnsiColor.RESET.getColorCode();
    }
}