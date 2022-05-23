package DAL.DataEntities.Enums;

import Common.Contracts.SelfDescriptable;

public enum DeviceType implements SelfDescriptable {
    ROUTER ("Маршрутизатор"),
    SWITCH ("Концентратор"),
    MEDIATOR ("Преобразователь");

    private final String description;

    DeviceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
