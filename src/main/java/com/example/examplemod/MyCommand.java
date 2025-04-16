package com.example.examplemod;

import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;

@Command("hello")
public class MyCommand {

    @Subcommand("print")
    public void print(ForgeCommandActor actor, int times) {
        for (int i = 0; i < times; i++) {
            actor.sendRawMessage("Hello!!");
        }
    }
}
