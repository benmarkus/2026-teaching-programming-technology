package rpgame.entities;

public abstract class Dragon extends Character
{
    protected final int MINIMUM_INCOMING_DAMAGE;

    protected Dragon(String name, int healthPoints, int attackPower, int minimumIncomingDamage) {
        super(name, healthPoints, attackPower);
        MINIMUM_INCOMING_DAMAGE = minimumIncomingDamage;
    }

    @Override public void handleIncomingAttack(int incomingAmount) {
        if (incomingAmount < MINIMUM_INCOMING_DAMAGE) {
            return;
        }

        super.handleIncomingAttack(incomingAmount);
    }
}
