package com.switchfully.service.dto;

public class UpdateProfessorDTO {
        private String firstName;
        private String lastName;

        public UpdateProfessorDTO(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
}
