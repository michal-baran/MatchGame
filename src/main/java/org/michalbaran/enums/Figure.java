package org.michalbaran.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Figure {
    SYR(AnsiColor.YELLOW),
    HED(AnsiColor.RED),
    GRY(AnsiColor.BLUE),
    ZGR(AnsiColor.BRIGHT_BLUE),
    TRE(AnsiColor.YELLOW),
    MCG(AnsiColor.GREEN),
    RON(AnsiColor.BRIGHT_GREEN),
    DRA(AnsiColor.BRIGHT_GREEN),
    HAR(AnsiColor.BRIGHT_RED),
    TON(AnsiColor.GREEN),
    HAG(AnsiColor.BRIGHT_YELLOW),
    HER(AnsiColor.WHITE),
    DUM(AnsiColor.CYAN),
    LUN(AnsiColor.BRIGHT_MAGENTA),
    FLR(AnsiColor.BRIGHT_CYAN);

    private final AnsiColor color;

    @Override
    public String toString() {
        return color.getColorCode() + name();
    }
}
