import input_data
import subprocess

workbook_path = "/home/themystery/Sithembile/PYSEBAL/Data/Meteo/MeteoInput.xlsx"
start_row=8
end_row=8

worksheet=input_data.get_worksheet(input_data.get_workbook(workbook_path))

in_region="{}".format(worksheet['D2'].value)

subprocess.call(['bash', 'g.cregion', '{}'.format(in_region)])

for row in range(start_row, end_row + 1):
    folder=worksheet['B{}'.format(row)].value
    date=worksheet['C{}'.format(row)].value
    subprocess.call(['bash', 'g.gldas.meteo', '{}'.format(folder), '{}'.format(date)])