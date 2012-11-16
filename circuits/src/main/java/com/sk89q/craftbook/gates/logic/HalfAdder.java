package com.sk89q.craftbook.gates.logic;

import com.sk89q.craftbook.ChangedSign;
import com.sk89q.craftbook.ic.*;
import org.bukkit.Server;

public class HalfAdder extends AbstractIC {

    public HalfAdder(Server server, ChangedSign block, ICFactory factory) {

        super(server, block, factory);
    }

    @Override
    public String getTitle() {

        return "Half Adder";
    }

    @Override
    public String getSignTitle() {

        return "HALF ADDER";
    }

    @Override
    public void trigger(ChipState chip) {

        boolean B = chip.getInput(1);
        boolean C = chip.getInput(2);

        boolean S = B ^ C;
        boolean Ca = B & C;

        chip.setOutput(0, S);
        chip.setOutput(1, Ca);
        chip.setOutput(2, Ca);
    }

    public static class Factory extends AbstractICFactory {

        public Factory(Server server) {

            super(server);
        }

        @Override
        public IC create(ChangedSign sign) {

            return new HalfAdder(getServer(), sign, this);
        }
    }
}