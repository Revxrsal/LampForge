package com.example.examplemod;

import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Named;
import revxrsal.commands.annotation.Range;
import revxrsal.commands.annotation.Subcommand;

@Command("hello")
public class MyCommand {

    @Subcommand("print")
    public void print(ForgeCommandActor actor, @Named("times") @Range(min = 1, max = 50) int times) {
        for (int i = 0; i < times; i++) {
            actor.sendRawMessage("Hello!!");
        }
    }
}
