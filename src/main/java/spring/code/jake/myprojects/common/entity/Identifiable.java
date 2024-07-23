package spring.code.jake.myprojects.common.entity;

public interface Identifiable<ID> {
    ID getId();

    void setId(ID id);
}
