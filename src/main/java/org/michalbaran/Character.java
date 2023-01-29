package org.michalbaran;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Character {
    SYR("Syriusz Black", AnsiColor.YELLOW),
    HED("Hedwiga", AnsiColor.RED),
    GRY("Gryfek", AnsiColor.BLUE),
    ZGR("Zgredek", AnsiColor.BRIGHT_BLUE),
    TRE("Sybilla Trelawney", AnsiColor.YELLOW),
    MCG("Minerwa McGonagall", AnsiColor.GREEN),
    RON("Ron Weasley", AnsiColor.BRIGHT_GREEN),
    DRA("Draco Malfoy", AnsiColor.BRIGHT_GREEN),
    HAR("Harry Potter", AnsiColor.BRIGHT_RED),
    TON("Nimfadora Tonks", AnsiColor.GREEN),
    HAG("Hagrid", AnsiColor.BRIGHT_YELLOW),
    HER("Hermiona Granger", AnsiColor.WHITE),
    DUM("Albus Dumbledore", AnsiColor.CYAN),
    LUN("Luna Lovegood", AnsiColor.BRIGHT_MAGENTA),
    FLR("Fleur Delacour", AnsiColor.BRIGHT_CYAN);

    private final String fullName;
    private final AnsiColor color;

    @Override
    public String toString() {
        return color.getColorCode() + name();
    }
}
