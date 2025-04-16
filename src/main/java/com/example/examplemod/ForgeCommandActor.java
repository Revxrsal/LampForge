package com.example.examplemod;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import revxrsal.commands.Lamp;
import revxrsal.commands.command.CommandActor;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public record ForgeCommandActor(CommandSourceStack source, Lamp<?> lamp) implements CommandActor {

    @Override
    public @NotNull String name() {
        return source.getTextName();
    }

    @Override
    public @NotNull UUID uniqueId() {
        if (source.getEntity() != null) {
            return source.getEntity().getUUID();
        }
        // test other command sources and create some sort of UUID from them?
        return UUID.nameUUIDFromBytes(name().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void sendRawMessage(@NotNull String message) {
        source.sendSystemMessage(Component.literal(message));
    }

    @Override
    public void sendRawError(@NotNull String message) {
        // 16733525 is RED according to TextColor
        source.sendSystemMessage(Component.literal(message).withColor(16733525));
    }

    @Override
    public @NotNull CommandSourceStack source() {
        return source;
    }

    // add your own utility methods here like sourceAsPlayer(), etc.
}
