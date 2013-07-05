package com.mtbs3d.minecrift.gui;

import net.minecraft.src.*;

/**
 * Created with IntelliJ IDEA.
 * User: Pete
 * Date: 7/4/13
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class GuiHUDSettings extends BaseGuiSettings
{
    static EnumOptions[] hudOptions = new EnumOptions[] {
//            EnumOptions.EYE_HEIGHT,
//            EnumOptions.RENDER_OWN_HEADWEAR,
//            EnumOptions.RENDER_PLAYER_OFFSET,
            EnumOptions.HUD_SCALE,
            EnumOptions.HUD_DISTANCE,
            EnumOptions.HUD_OPACITY,
    };

    public GuiHUDSettings(GuiScreen guiScreen, GameSettings guiGameSettings) {
        super( guiScreen, guiGameSettings );
        screenTitle = "HUD / Overlay Settings";
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        StringTranslate stringTranslate = StringTranslate.getInstance();
        this.buttonList.clear();
//        this.buttonList.add(new GuiSmallButtonEx(EnumOptions.USE_VR.returnEnumOrdinal(), this.width / 2 - 78, this.height / 6 - 14, EnumOptions.USE_VR, this.guiGameSettings.getKeyBinding(EnumOptions.USE_VR)));
        this.buttonList.add(new GuiButtonEx(201, this.width / 2 - 100, this.height / 6 + 148, "Reset To Defaults"));
        this.buttonList.add(new GuiButtonEx(200, this.width / 2 - 100, this.height / 6 + 168, stringTranslate.translateKey("gui.done")));
        EnumOptions[] buttons = hudOptions;

        for (int var12 = 2; var12 < buttons.length + 2; ++var12)
        {
            EnumOptions var8 = buttons[var12 - 2];
            int width = this.width / 2 - 155 + var12 % 2 * 160;
            int height = this.height / 6 + 21 * (var12 / 2) - 10;

            if (var8.getEnumFloat())
            {
                float minValue = 0.0f;
                float maxValue = 1.0f;
                float increment = 0.01f;

//                if (var8 == EnumOptions.EYE_HEIGHT)
//                {
//                    minValue = 1.62f;
//                    maxValue = 1.85f;
//                    increment = 0.01f;
//                }
//                if (var8 == EnumOptions.RENDER_PLAYER_OFFSET)
//                {
//                    minValue = 0.0f;
//                    maxValue = 0.25f;
//                    increment = 0.01f;
//                }

                if (var8 == EnumOptions.HUD_SCALE)
                {
                    minValue = 0.5f;
                    maxValue = 1.5f;
                    increment = 0.01f;
                }
                if (var8 == EnumOptions.HUD_DISTANCE)
                {
                    minValue = 0.5f;
                    maxValue = 3.0f;
                    increment = 0.02f;
                }

                this.buttonList.add(new GuiSliderEx(var8.returnEnumOrdinal(), width, height, var8, this.guiGameSettings.getKeyBinding(var8), minValue, maxValue, increment, this.guiGameSettings.getOptionFloatValue(var8)));
            }
            else
            {
                this.buttonList.add(new GuiSmallButtonEx(var8.returnEnumOrdinal(), width, height, var8, this.guiGameSettings.getKeyBinding(var8)));
            }
        }
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.enabled)
        {
            if (par1GuiButton.id < 200 && par1GuiButton instanceof GuiSmallButtonEx)
            {
                EnumOptions num = EnumOptions.getEnumOptions(par1GuiButton.id);
                this.guiGameSettings.setOptionValue(((GuiSmallButtonEx)par1GuiButton).returnEnumOptions(), 1);
                par1GuiButton.displayString = this.guiGameSettings.getKeyBinding(EnumOptions.getEnumOptions(par1GuiButton.id));
            }
            else if (par1GuiButton.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.parentGuiScreen);
            }
            else if (par1GuiButton.id == 201)
            {
                this.mc.gameSettings.saveOptions();
            }
        }
    }

    @Override
    protected String[] getTooltipLines(String displayString, int buttonId)
    {
        EnumOptions e = EnumOptions.getEnumOptions(buttonId);
        if( e != null )
            switch(e)
            {
                case HUD_OPACITY:
                    return new String[] {
                            "Whether the in-game HUD and UI are slightly transparent",
                            "  ON: HUD and UI are transparent",
                            "  OFF: HUD and UI are opaque"
                    };
                case HUD_SCALE:
                    return new String[] {
                            "Relative size HUD takes up in field-of-view",
                            "  The units are just relative, not in degrees",
                            "  or a fraction of FOV or anything"
                    };
                case HUD_DISTANCE:
                    return new String[] {
                            "Distance the floating HUD is drawn in front of your body",
                            "  The relative size of the HUD is unchanged by this",
                            "  Distance is in meters (though isn't obstructed by blocks)"
                    };
                default:
                    return null;
            }
        else
            switch(buttonId)
            {
//                case 201:
//                    return new String[] {
//                            "Open this configuration screen to adjust the Head",
//                            "  Tracker orientation (direction) settings. ",
//                            "  Ex: Head Tracking Selection (Hydra/Oculus), Prediction"
//                    };
                default:
                    return null;
            }
    }
}
