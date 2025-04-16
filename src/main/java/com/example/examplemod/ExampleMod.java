package com.example.examplemod;

import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import revxrsal.commands.Lamp;
import revxrsal.commands.brigadier.BrigadierConverter;
import revxrsal.commands.brigadier.BrigadierParser;

@Mod(ExampleMod.MODID)
public class ExampleMod {

    public static final String MODID = "examplemod";

    public ExampleMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        BrigadierConverter<ForgeCommandActor, CommandSourceStack> converter = new ForgeBrigadierConverter();
        BrigadierParser<CommandSourceStack, ForgeCommandActor> parser = new BrigadierParser<>(converter);
        RootCommandNode<CommandSourceStack> lampCommands = new RootCommandNode<>();
        Lamp<ForgeCommandActor> lamp = Lamp.<ForgeCommandActor>builder()
                // register forge types here as you need
                .senderResolver(new ForgeSenderResolver())
                .exceptionHandler(new ForgeExceptionHandler())
                .hooks(hooks -> hooks.onCommandRegistered((command, cancelHandle) -> {
                    BrigadierParser.addChild(lampCommands, parser.createNode(command));
                }))
                .build();

        lamp.register(new MyCommand()); // register your commands here

        for (CommandNode<CommandSourceStack> child : lampCommands.getChildren()) {
            // use BrigadierParser.addChild and not root.addChild!
            //
            // our own addChild implementation handles permissions better than
            // the default brigadier one.
            BrigadierParser.addChild(event.getDispatcher().getRoot(), child);
        }
    }
}
