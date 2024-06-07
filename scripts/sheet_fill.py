# Author: Shubert Munthali
# Copyright (c) 2024 Shubert Munthali
# Apache License

import os
import input_data

workbook_path=""
meteo_workbook_path = ""
start_row=2
end_row=33
start_col=2
end_col=15

workbook = input_data.get_workbook(workbook_path)
meteo_workbook = input_data.get_workbook(meteo_workbook_path)
worksheet=input_data.get_worksheet_by_name(workbook, 'Meteo_Input')
meteo_sheet=input_data.get_worksheet(meteo_workbook)

for col in range(start_col, end_col +1):
    col_name=worksheet.cell(1, col).value
    for row in range(start_row, end_row + 1):
        path = '{}/PySEBAL_Weather'.format(meteo_sheet.cell(row, 2).value)
        files = os.listdir(path)
        param=''
        for file in files:
            if "Tair_inst" in file and col_name == "Temp_Inst" and file.endswith(".tif"):
                param='{}/{}'.format(path, file)
            if "Tair_24" in file and col_name == "Temp_24" and file.endswith(".tif"):
                param='{}/{}'.format(path, file)
            if "Rh_inst" in file and col_name == "RH_inst" and file.endswith(".tif"):
                param='{}/{}'.format(path, file)
            if "Rh_24" in file and col_name == "RH_24" and file.endswith(".tif"):
                param='{}/{}'.format(path, file)
            if "Wind_inst" in file and col_name == "Wind_inst" and file.endswith(".tif"):
                param='{}/{}'.format(path, file)
            if "Wind_24" in file and col_name == "Wind_24" and file.endswith(".tif"):
                param='{}/{}'.format(path, file)
            if "SWdown_24" in file and col_name == "Rs_24" and file.endswith(".tif"):
                param='{}/{}'.format(path, file)
            if "SWdown_inst" in file and col_name == "Rs_in_inst_cloud_free" and file.endswith(".tif"):
                param='{}/{}'.format(path, file)
        if param != '':
            worksheet.cell(row, col).value = param
workbook.save(workbook_path)
print("Writing meteo data complete")