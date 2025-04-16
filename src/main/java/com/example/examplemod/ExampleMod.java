package com.example.examplemod;

import com.mojang.brigadier.tree.LiteralCommandNode;
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
        Lamp<ForgeCommandActor> lamp = Lamp.<ForgeCommandActor>builder()
                // register forge types here as you need
                .senderResolver(new ForgeSenderResolver())
                .exceptionHandler(new ForgeExceptionHandler())
                .hooks(hooks -> hooks.onCommandRegistered((command, cancelHandle) -> {
                    // use BrigadierParser.addChild and not root.addChild!
                    //
                    // our own addChild implementation handles permissions better than
                    // the default brigadier one.
                    LiteralCommandNode<CommandSourceStack> node = parser.createNode(command);
                    BrigadierParser.addChild(event.getDispatcher().getRoot(), node);
                }))
                .build();

        lamp.register(new MyCommand()); // register your commands here
    }
}
