import patientItems from "./handle_items.js";

export default function dropdownPatientRender() {
      const patient_dropdowns = [
       $("#patient-profile"),
       $("#patient-alert"),
       $("#patient-appointment"),
       $("#patient-profile-appointment")
   ];
    patientItems(patient_dropdowns)
}