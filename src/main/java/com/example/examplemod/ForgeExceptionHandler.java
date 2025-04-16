package com.example.examplemod;

import revxrsal.commands.exception.DefaultExceptionHandler;

public class ForgeExceptionHandler extends DefaultExceptionHandler<ForgeCommandActor> {

    @HandleException
    public void onSenderNotPlayer(SenderNotPlayerException e, ForgeCommandActor actor) {
        actor.reply("You must be a player to use this command!");
    }
}
