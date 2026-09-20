package rpgame.entities;

public class Defender extends Orc
{
    public Defender(String name, int healthPoints, int attackPower) {
        super(name, healthPoints, attackPower);
    }

    @Override
    public void handleIncomingAttack(int incomingAmount) {
        super.handleIncomingAttack(incomingAmount / 2);
    }
}
