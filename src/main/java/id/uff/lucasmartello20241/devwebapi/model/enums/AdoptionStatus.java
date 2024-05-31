package id.uff.lucasmartello20241.devwebapi.model.enums;

public enum AdoptionStatus {
    WAITING_ADOPTION("Waiting adoption"),
    ADOPTED("Adopted");
    
    public final String value;

    private AdoptionStatus(String value) {
        this.value = value;
    }
}
