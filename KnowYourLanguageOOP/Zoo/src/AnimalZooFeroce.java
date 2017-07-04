/**
 * Created by Mihaela.Stoian on 7/4/2017.
 */
public class AnimalZooFeroce extends Animal {
    @Override
    public void mananca(Object obj) {
        System.out.println("AnimalulZooFeroce mananca.");
    }

    @Override
    public void seJoaca() {
        System.out.println("AnimalulZooFeroce se joaca.");
    }

    @Override
    public void faceBaie() {
        System.out.println("AnimalulZooFeroce face baie.");
    }

    @Override
    public void doarme() {
        super.doarme();
        System.out.println("Animalul feroce vaneaza.");
    }
}
