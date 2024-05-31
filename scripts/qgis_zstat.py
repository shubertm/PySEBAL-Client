
import os
import subprocess
import input_data
import csv
from statistics import mean

input_shape="/home/themystery/Sithembile/PYSEBAL/Data/Kasinthula.shp"
workbook_path="/home/themystery/Sithembile/PYSEBAL/Data/Meteo/Meteo.xlsx"
meteo_workbook_path = "/home/themystery/Sithembile/PYSEBAL/Data/Meteo/MeteoInput.xlsx"
start_row=2
end_row=33
start_col=1
end_col=9

workbook = input_data.get_workbook(workbook_path)
meteo_workbook = input_data.get_workbook(meteo_workbook_path)
worksheet=input_data.get_worksheet_by_name(workbook, 'Meteo_Input')
meteo_sheet=input_data.get_worksheet(meteo_workbook)

for col in range(start_col, end_col +1):
    col_name=worksheet.cell(1, col).value
    for row in range(start_row, end_row + 1):
        path = '{}/PySEBAL_Weather'.format(meteo_sheet.cell(row, 2).value)
        date = meteo_sheet.cell(row, 3).value
        files = os.listdir(path)
        raster= ''
        for file in files:

            if "Tair_inst" in file and col_name == "Temp_inst" and file.endswith(".tif"):
                raster= '{}/{}'.format(path, file)
            if "Tair_24" in file and col_name == "Temp_24" and file.endswith(".tif"):
                raster= '{}/{}'.format(path, file)
            if "Rh_inst" in file and col_name == "RH_inst" and file.endswith(".tif"):
                raster= '{}/{}'.format(path, file)
            if "Rh_24" in file and col_name == "RH_24" and file.endswith(".tif"):
                raster= '{}/{}'.format(path, file)
            if "Wind_inst" in file and col_name == "Wind_inst" and file.endswith(".tif"):
                raster= '{}/{}'.format(path, file)
            if "Wind_24" in file and col_name == "Wind_24" and file.endswith(".tif"):
                raster= '{}/{}'.format(path, file)
            if "SWdown_24" in file and col_name == "Rs_24" and file.endswith(".tif"):
                raster= '{}/{}'.format(path, file)
            if "SWdown_inst" in file and col_name == "Rs_inst" and file.endswith(".tif"):
                raster= '{}/{}'.format(path, file)

        if col == 1:
            worksheet.cell(row, col).value = date

        if raster != '':

            zfilename='{}_{}.csv'.format(date, col_name)

            output='/home/themystery/Sithembile/PYSEBAL/Data/Meteo/{}'.format(zfilename)

            subprocess.call(['bash', 'qgs.zstat', '{}'.format(input_shape), '{}'.format(output), '{}'.format(raster)])

            if os.path.exists(output):
                with open(output, 'r') as file:
                    csvreader = csv.reader(file)
                    v = []

                    for csv_row in csvreader:
                        val = csv_row[1]
                        if val != "_mean":
                            v.append(float(val))

                    worksheet.cell(row, col).value = mean(v)
                    workbook.save(workbook_path)
                    os.remove(output)

print("Writing meteo data complete")