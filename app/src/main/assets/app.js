document.addEventListener("DOMContentLoaded", function () {
    const searchButton = document.getElementById("searchButton");
    const destinationInput = document.getElementById("destinationInput");
    const resultText = document.getElementById("resultText");

    if (searchButton) {
        searchButton.addEventListener("click", function () {
            const destination = destinationInput
                ? destinationInput.value.trim()
                : "";

            if (destination === "") {
                if (resultText) {
                    resultText.textContent = "Please enter your destination.";
                }
                return;
            }

            if (resultText) {
                resultText.textContent =
                    "Searching for: " + destination;
            }
        });
    }
});
