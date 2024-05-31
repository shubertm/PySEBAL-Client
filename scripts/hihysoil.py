import subprocess

input_folder="/home/themystery/Sithembile/PYSEBAL/Data/Soil/Kasinthula"
dem="/home/themystery/Sithembile/PYSEBAL/Data/DEM/Kasinthula/dem.tif"

subprocess.call(['bash', 'g.hihysoil', '{}'.format(input_folder), '{}'.format(dem)])
