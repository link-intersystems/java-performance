package com.link_intersystems.es.employee.event;

public interface EmployeeEventVisitor {


    void visit(HireEmployeeEvent hireEmployeeEvent);

    void visit(ChangeEmploymentTitleEvent changeEmploymentTitleEvent);

    void visit(ChangeSalaryEvent changeSalaryEvent);

    void visit(MoveEmployeeEvent moveEmployeeEvent);

    void visit(RenameEmployeeEvent renameEmployeeEvent);

    void visit(QuitEvent quitEvent);
}
