const destinationInput = document.getElementById("destination");
const navigateBtn = document.getElementById("navigateBtn");
const clearBtn = document.getElementById("clearBtn");
const message = document.getElementById("message");
const modes = document.querySelectorAll(".mode");

let selectedMode = "driving";

modes.forEach(function (button) {
    button.addEventListener("click", function () {

        modes.forEach(function (item) {
            item.classList.remove("active");
        });

        button.classList.add("active");

        selectedMode = button.getAttribute("data-mode");

        message.textContent =
            "Transport mode selected: " +
            selectedMode.charAt(0).toUpperCase() +
            selectedMode.slice(1);
    });
});

clearBtn.addEventListener("click", function () {
    destinationInput.value = "";
    message.textContent = "Enter a destination to begin.";
    destinationInput.focus();
});

navigateBtn.addEventListener("click", function () {

    const destination = destinationInput.value.trim();

    if (destination === "") {
        message.textContent = "Please enter a destination first.";
        destinationInput.focus();
        return;
    }

    const destinationEncoded = encodeURIComponent(destination);

    const mapsUrl =
        "https://www.google.com/maps/dir/?api=1" +
        "&destination=" + destinationEncoded +
        "&travelmode=" + selectedMode;

    message.textContent = "Opening Google Maps...";

    window.location.href = mapsUrl;
});

destinationInput.addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
        navigateBtn.click();
    }
});
