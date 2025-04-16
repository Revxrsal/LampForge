package com.example.examplemod;

import revxrsal.commands.annotation.*;

@Command("hello")
public class MyCommand {

    @Subcommand("print")
    public void print(
            ForgeCommandActor actor,
            String message,
            @Flag @Range(min = 1, max = 50) int times,
            @Switch boolean broadcast,
            @Switch boolean color
    ) {
        for (int i = 0; i < times; i++) {

            actor.sendRawMessage(message);
        }
    }
}
