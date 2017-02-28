/*
 * CraftBook Copyright (C) 2010-2017 sk89q <http://www.sk89q.com>
 * CraftBook Copyright (C) 2011-2017 me4502 <http://www.me4502.com>
 * CraftBook Copyright (C) Contributors
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.sk89q.craftbook.sponge.util.data;

import com.sk89q.craftbook.sponge.mechanics.blockbags.data.BlockBagDataManipulatorBuilder;
import com.sk89q.craftbook.sponge.util.data.builder.ICDataManipulatorBuilder;
import com.sk89q.craftbook.sponge.util.data.builder.LastPowerDataManipulatorBuilder;
import com.sk89q.craftbook.sponge.util.data.builder.NamespaceDataBuilder;
import com.sk89q.craftbook.sponge.mechanics.blockbags.data.ImmutableBlockBagData;
import com.sk89q.craftbook.sponge.util.data.immutable.ImmutableICData;
import com.sk89q.craftbook.sponge.util.data.immutable.ImmutableLastPowerData;
import com.sk89q.craftbook.sponge.util.data.immutable.ImmutableNamespaceData;
import com.sk89q.craftbook.sponge.mechanics.blockbags.data.BlockBagData;
import com.sk89q.craftbook.sponge.util.data.mutable.ICData;
import com.sk89q.craftbook.sponge.util.data.mutable.LastPowerData;
import com.sk89q.craftbook.sponge.util.data.mutable.NamespaceData;
import org.spongepowered.api.Sponge;

public class CraftBookData {

    public static void registerData() {
        Sponge.getDataManager().register(LastPowerData.class, ImmutableLastPowerData.class, new LastPowerDataManipulatorBuilder());
        Sponge.getDataManager().register(ICData.class, ImmutableICData.class, new ICDataManipulatorBuilder());
        Sponge.getDataManager().register(NamespaceData.class, ImmutableNamespaceData.class, new NamespaceDataBuilder());
    }
}
