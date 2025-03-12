package ubb.scs.mpp.model;

public interface Identifiable<Tid> {
    Tid getID();
    void setID(Tid id);
}
