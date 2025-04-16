package com.example.examplemod;

import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Flag;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.annotation.Switch;

@Command("hello")
public class MyCommand {

    @Subcommand("print")
    public void print(
            ForgeCommandActor actor,
            String message,
            @Flag int times,
            @Switch boolean broadcast,
            @Switch boolean color
    ) {
        for (int i = 0; i < times; i++) {

            actor.sendRawMessage(message);
        }
    }
}
