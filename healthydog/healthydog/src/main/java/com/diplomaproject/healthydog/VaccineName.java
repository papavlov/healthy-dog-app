package com.diplomaproject.healthydog;

public enum VaccineName {
    PUPPY_1ST_ROUND,     // Distemper, Parvovirus, Adenovirus
    PUPPY_2ND_ROUND,     // Booster (DHPP) + Rabies
    ANNUAL_VACCINE,      // DHPP + Rabies
    OPTIONAL_LEPTOSPIROSIS,
    OPTIONAL_BORDETELLA,
    OPTIONAL_LYME,
    OPTIONAL_INFLUENZA;

    public String getFormattedName() {
        switch (this) {
            case PUPPY_1ST_ROUND:
                return "Puppy 1st Round (Distemper, Parvovirus, Adenovirus)";
            case PUPPY_2ND_ROUND:
                return "Puppy 2nd Round (Booster (DHPP) + Rabies)";
            case ANNUAL_VACCINE:
                return "Annual Vaccine (DHPP + Rabies)";
            case OPTIONAL_LEPTOSPIROSIS:
                return "Leptospirosis (Optional)";
            case OPTIONAL_BORDETELLA:
                return "Bordetella (Optional)";
            case OPTIONAL_LYME:
                return "Lyme (Optional)";
            case OPTIONAL_INFLUENZA:
                return "Influenza (Optional)";
            default:
                return name().replace('_', ' ').toLowerCase();
        }
    }
}

