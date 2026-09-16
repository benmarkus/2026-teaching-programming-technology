package rpgame.entities;

public class MainCharacter extends Character
{
    private final int defense;

    public MainCharacter(String name, int healthPoints, int attackPower, int defense) {
        super(name, healthPoints, attackPower);
        this.defense = defense;
    }

    @Override
    public void handleIncomingAttack(int incomingAmount) {
        super.handleIncomingAttack(dampenDamage(incomingAmount));
    }

    public int dampenDamage(int amount) {
        return amount / defense;
    }
}
