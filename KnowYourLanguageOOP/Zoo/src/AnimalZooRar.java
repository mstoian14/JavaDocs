import exceptions.AnimalManancaOmException;

/**
 * Created by Mihaela.Stoian on 7/4/2017.
 */
public class AnimalZooRar extends Animal {
    private String nume;
    private String numeleTariiDeOrigine;

    public AnimalZooRar() {
        this.nume = null;
        this.numeleTariiDeOrigine = null;
    }

    public AnimalZooRar(String nume, String numeleTariiDeOrigine) {
        this.nume = nume;
        this.numeleTariiDeOrigine = numeleTariiDeOrigine;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setNumeleTariiDeOrigine(String numeleTariiDeOrigine) {
        this.numeleTariiDeOrigine = numeleTariiDeOrigine;
    }

    public String getNume() {
        return nume;
    }

    public String getNumeleTariiDeOrigine() {
        return numeleTariiDeOrigine;
    }

    @Override
    public void faceBaie() {
        System.out.println("AnimalulZooRar face baie");
    }

    @Override
    public void seJoaca() {
        System.out.println("AnimalulZooRar se joaca");
        super.doarme();
    }

    @Override
    public void mananca(Object obj) throws AnimalManancaOmException {
        if(obj instanceof AngajatZoo) {
            throw new AnimalManancaOmException("Nu este AnimalZooRar, ci AngajatZoo.");
        } else {
            System.out.println("AnimalulZooRar mananca.");
        }
    }
}
