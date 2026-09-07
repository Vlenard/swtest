function calculateGrade(theory, practice) {
    if (!Number.isFinite(theory) || !Number.isFinite(practice) ||
        theory < 0 || theory > 60 || practice < 0 || practice > 40) {
        throw new RangeError("Invalid score");
    }

    if (theory < 30 || practice < 20) {
        return 1;
    }

    const total = theory + practice;

    if (total < 50) {
        return 1;
    } else if (total <= 59) {
        return 2;
    } else if (total <= 69) {
        return 3;
    } else if (total <= 84) {
        return 4;
    }

    return 5;
}

export { calculateGrade };
