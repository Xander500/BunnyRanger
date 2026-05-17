package io.BunnyRanger;

import com.badlogic.gdx.graphics.Color;

public class DamageCalculator {

    public enum DamageType {
        REGULAR,
        MAGIC
    }

    public static class Palette {
        private final Color regularColor;
        private final Color critColor;
        private final Color magicColor;
        private final Color magicCritColor;

        public Palette(Color regularColor, Color critColor, Color magicColor, Color magicCritColor) {
            this.regularColor = new Color(regularColor);
            this.critColor = new Color(critColor);
            this.magicColor = new Color(magicColor);
            this.magicCritColor = new Color(magicCritColor);
        }

        public Color getRegularColor() {
            return new Color(regularColor);
        }

        public Color getCritColor() {
            return new Color(critColor);
        }

        public Color getMagicColor() {
            return new Color(magicColor);
        }

        public Color getMagicCritColor() {
            return new Color(magicCritColor);
        }
    }

    public static class Result {
        private final float amount;
        private final boolean crit;
        private final boolean dodged;
        private final DamageType type;
        private final Color color;

        public Result(float amount, boolean crit, boolean dodged, DamageType type, Color color) {
            this.amount = amount;
            this.crit = crit;
            this.dodged = dodged;
            this.type = type;
            this.color = color;
        }

        public float getAmount() {
            return amount;
        }

        public boolean isCrit() {
            return crit;
        }

        public boolean isDodged() {
            return dodged;
        }

        public DamageType getType() {
            return type;
        }

        public Color getColor() {
            return new Color(color);
        }
    }

    public static Palette defaultPalette() {
        return new Palette(
            Color.LIGHT_GRAY,
            Color.GREEN,
            new Color(.75f, .45f, 1f, 1f),
            new Color(.25f, 0f, .45f, 1f)
        );
    }

    public static Result calculate(Entity source, Damageable target, float baseDamage, DamageType type, Palette palette) {
        float damage = Math.max(baseDamage, 0f);
        boolean crit = false;
        boolean dodged = false;

        if (source instanceof Player) {
            Player player = (Player) source;

            if (type == DamageType.MAGIC) {
                damage *= 1f + (player.getSpecialLevel() * .02f);
            } else {
                damage *= 1f + (player.getAttackLevel() * .02f);
            }

            crit = Math.random() < Math.min(player.getCritChance(), 1f);
            if (crit) {
                damage *= 2f;
            }
        }

        if (target instanceof Player) {
            Player player = (Player) target;
            dodged = Math.random() < Math.min(player.getDodgeChance(), .95f);

            if (dodged) {
                damage = 0f;
            } else {
                damage = Math.max(0f, damage - player.getDefenseReduction());
            }
        }

        return new Result(damage, crit, dodged, type, getColor(type, crit, palette));
    }

    private static Color getColor(DamageType type, boolean crit, Palette palette) {
        if (type == DamageType.MAGIC) {
            return crit ? palette.getMagicCritColor() : palette.getMagicColor();
        }

        return crit ? palette.getCritColor() : palette.getRegularColor();
    }
}
