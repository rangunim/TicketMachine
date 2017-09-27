package net.mstezala.services.Slots;

public class SlotInfo {
    private Slot slot;

    public SlotInfo(Slot slot) {
        this.slot = slot;
    }

    public String getInfo() {
        final StringBuilder builder = new StringBuilder();
        builder.append("jest " + slot.getObjectNumber().size() + " rodzajów:\n");
        slot.getObjectNumber().entrySet().forEach(c -> builder.append(c).append("\n"));

        builder.append("Ich maksymalna ilosc (limity) to: \n");
        slot.getLimitObjectNumber().entrySet().forEach(c -> builder.append(c).append("\n"));
        return builder.toString();
    }
}
