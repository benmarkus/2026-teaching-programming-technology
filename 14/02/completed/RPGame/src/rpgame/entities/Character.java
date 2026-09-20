package rpgame.entities;

public abstract class Character implements Attackable
{
    protected String name;
    protected int healthPoints;
    protected int attackPower;

    protected Character(String name, int healthPoints, int attackPower) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.attackPower = attackPower;
    }

    public void attack(Attackable target) {
        target.handleIncomingAttack(this.attackPower);
    }

    @Override public void handleIncomingAttack(int incomingAmount) {
        this.takeDamage(incomingAmount);
    }

    public boolean isAlive() {
        return this.healthPoints > 0;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public String getName() {
        return this.name;
    }

    private void takeDamage(int amount) {
        this.healthPoints = Math.max(0, this.healthPoints - amount);
    }
}
