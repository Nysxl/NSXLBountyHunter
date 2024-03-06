package com.internal.Nysxl.NSXLBountyHunter.BountyGUI;

import com.internal.Nysxl.NSXLBountyHunter.Bounties.Bounties;
import com.internal.Nysxl.NSXLBountyHunter.main;
import com.internal.nysxl.NysxlUtilities.GUIManager.Buttons.DynamicButton;
import com.internal.nysxl.NysxlUtilities.GUIManager.DynamicListGUI;
import com.internal.nysxl.NysxlUtilities.ItemBuilder.ItemFactory;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;

public class BountyGUI {

    /**
     * creates a new bounty gui.
     * @param player the player the gui will be opened for.
     */
    public BountyGUI(Player player){
        DynamicListGUI gui = new DynamicListGUI("Bounties", 54);
        populateGUI(gui, main.activeBounties);
        gui.open(player);
    }

    /**
     * populates the bounty gui with all active bounties.
     * @param gui the gui for the bounty.
     * @param bounties all active bounties.
     */
    public void populateGUI(DynamicListGUI gui, ArrayList<Bounties> bounties){
        if(bounties.isEmpty()) return;

        for(Bounties bounty : bounties) {
            ItemFactory factory = new ItemFactory(bounty.playerHead());
            factory.setLore("Bounty: " + bounty.reward());
            gui.addItem(factory.buildItem(), new DynamicButton(0, factory.buildItem()));
        }
    }


}
