let display = document.querySelector('.display');
let ans = 0;

function appendToDisplay(value) {
  display.value += value;
}

function clearDisplay() {
  display.value = '';
  ans = 0;
}

function calculate() {
  try {
    let result = eval(display.value);
    display.value = result;
    ans = result; // Update the answer
  } catch (error) {
    display.value = 'Error';
  }
}

function calculatePercentage() {
  try {
    let result = eval(display.value) / 100;
    display.value = result;
  } catch (error) {
    display.value = 'Error';
  }
}

function togglePositiveNegative() {
  try {
    let currentValue = parseFloat(display.value);
    display.value = -currentValue;
  } catch (error) {
    display.value = 'Error';
  }
}

function deleteLastEntry() {
  let value = display.value;
  if (value === 'Error' || value === 'undefined' || typeof value === 'undefined' || isNaN(value)) {
    display.value = '';
  } else if (value.slice(-3) === 'ans') {
    display.value = value.slice(0, -3);
  } else {
    display.value = value.slice(0, -1);
  }
}