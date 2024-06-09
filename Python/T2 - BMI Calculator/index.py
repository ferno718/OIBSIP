import tkinter as tk
from tkinter import ttk
from matplotlib.figure import Figure
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg

def calculate_bmi():
    try:
        weight = float(weight_entry.get())
        height = float(height_entry.get()) / 100  
        bmi = round(weight / (height ** 2), 2)

        # Determine the health classification
        if bmi < 18.5:
            category = 'Underweight'
        elif 18.5 <= bmi < 25:
            category = 'Normal weight'
        elif 25 <= bmi < 30:
            category = 'Overweight'
        else:
            category = 'Obese'

        result_label.config(text=f"Your BMI:  {bmi} \nCategory: {category}")
        update_plot(bmi)
    except ValueError:
        result_label.config(text="Invalid input. Please enter numbers only.", fg="red")

def reset_fields():
    weight_entry.delete(0, tk.END)
    height_entry.delete(0, tk.END)
    result_label.config(text="")
    update_plot(None)

def update_plot(bmi):
    ax.clear()
    ax.barh(categories, bmi_levels, color=colors, edgecolor='black')
    ax.axvline(x=bmi, color='black', linewidth=2, label=f'Your BMI: {bmi}' if bmi else '')
    ax.set_xlim(0, 40)
    ax.set_xlabel('BMI')
    ax.set_yticklabels(categories, fontsize=10)
    ax.legend()
    fig.tight_layout(pad=2)
    canvas.draw()

# Setting up the main window
root = tk.Tk()
root.title("BMI Calculator")

# Adding entry widgets and labels
ttk.Label(root, text="Enter your weight (kg):").grid(row=0, column=0, sticky=tk.W, padx=5, pady=10)
weight_entry = ttk.Entry(root)
weight_entry.grid(row=0, column=1, padx=5, pady=10)

ttk.Label(root, text="Enter your height (cm):").grid(row=1, column=0, sticky=tk.W, padx=5, pady=5)
height_entry = ttk.Entry(root)
height_entry.grid(row=1, column=1, padx=5, pady=5)

# Add a button to calculate BMI
calculate_button = ttk.Button(root, text="Calculate BMI", command=calculate_bmi)
calculate_button.grid(row=2, column=0, columnspan=2, pady=20)

# Add a button to reset fields
reset_button = ttk.Button(root, text="Reset", command=reset_fields)
reset_button.grid(row=3, column=0, columnspan=2, pady=5)

# Label to display the BMI result
result_label = ttk.Label(root, text="", font=('Helvetica', 12, 'bold'))
result_label.grid(row=4, column=0, columnspan=2, pady=20)

# Create a matplotlib figure and axis
fig = Figure(figsize=(7, 5), dpi=100)
ax = fig.add_subplot(111)

# Define BMI categories and levels
categories = ['Underweight', 'Normal weight', 'Overweight', 'Obese']
bmi_levels = [18.5, 24.9, 29.9, 40]
colors = ['skyblue', 'lightgreen', '#edbb00', 'red']

# Plot the initial BMI levels
ax.barh(categories, bmi_levels, color=colors, edgecolor='black')
ax.set_xlim(0, 45)
ax.set_xlabel('BMI')
ax.set_yticklabels(categories, fontsize=10)
fig.tight_layout(pad=2)

# Create a canvas to display the plot
canvas = FigureCanvasTkAgg(fig, master=root)
canvas.draw()
canvas.get_tk_widget().grid(row=5, column=0, columnspan=2, pady=20)

root.mainloop()