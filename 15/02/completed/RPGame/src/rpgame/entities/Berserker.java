package rpgame.entities;

public class Berserker extends Orc
{
    public Berserker(String name, int healthPoints, int attackPower) {
        super(name, healthPoints, attackPower);
    }

    @Override public void handleIncomingAttack(int incomingAmount) {
        super.handleIncomingAttack(incomingAmount * 2);
    }
}
