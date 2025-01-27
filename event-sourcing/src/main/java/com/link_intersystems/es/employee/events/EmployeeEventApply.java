package com.link_intersystems.es.employee.events;

import com.link_intersystems.es.Employee;

import java.util.List;

public abstract class EmployeeEventApply {

    private Employee employee;

    private EmployeeEventVisitor visitor = new EmployeeEventVisitor() {
        @Override
        public void visit(HireEmployeeEvent hireEmployeeEvent) {
            if (employee != null) {
                throw new IllegalStateException("Employee has already been hired");
            }

            employee = doVisit(hireEmployeeEvent);
        }

        @Override
        public void visit(ChangeEmploymentTitleEvent changeEmploymentTitleEvent) {
            doVisit(checkEvent(changeEmploymentTitleEvent), employee);

        }

        private <T extends EmployeeEvent> T checkEvent(T employeeEvent) {
            if (employee == null) {
                throw new IllegalStateException("Employee has not been hired");
            }

            int employeeNumber = employeeEvent.getEmployeeNumber();
            if (employeeNumber != employee.getEmployeeNumber()) {
                throw new IllegalStateException("Can not apply " + employeeEvent + " to " + employee);
            }
            return employeeEvent;
        }

        @Override
        public void visit(ChangeSalaryEvent changeSalaryEvent) {
            doVisit(checkEvent(changeSalaryEvent), employee);
        }

        @Override
        public void visit(MoveEmployeeEvent moveEmployeeEvent) {
            doVisit(checkEvent(moveEmployeeEvent), employee);
        }

        @Override
        public void visit(RenameEmployeeEvent renameEmployeeEvent) {
            doVisit(checkEvent(renameEmployeeEvent), employee);
        }

        @Override
        public void visit(QuitEvent quitEvent) {
            doVisit(checkEvent(quitEvent), employee);
        }
    };

    public Employee apply(List<EmployeeEvent> employeeEvents) {
        employeeEvents.sort(EmployeeEvent.TEMPORAL_SORTER);
        employeeEvents.forEach(e -> e.accept(visitor));
        try {
            return employee;
        } finally {
            employee = null;
        }
    }


    protected abstract Employee doVisit(HireEmployeeEvent hireEmployeeEvent);

    protected abstract void doVisit(ChangeEmploymentTitleEvent changeEmploymentTitleEvent, Employee employee);

    protected abstract void doVisit(ChangeSalaryEvent changeSalaryEvent, Employee employee);

    protected abstract void doVisit(MoveEmployeeEvent moveEmployeeEvent, Employee employee);

    protected abstract void doVisit(RenameEmployeeEvent renameEmployeeEvent, Employee employee);

    protected abstract void doVisit(QuitEvent quitEvent, Employee employee);
}
