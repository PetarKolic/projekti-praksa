import random as random
import numpy as np
import matplotlib.pyplot as plt
import math as math

fajl =[173669, 275487, 1197613, 1549805, 502334, 217684, 1796841, 274708,
631252, 148665, 150254, 4784408, 344759, 440109, 4198037, 329673, 28602,
144173, 1461469, 187895, 369313, 959307, 1482335, 2772513, 1313997, 254845,
486167, 2667146, 264004, 297223, 94694, 1757457, 576203, 8577828, 498382,
8478177, 123575, 4062389, 3001419, 196884, 617991, 421056, 3017627, 131936,
1152730, 2676649, 656678, 4519834, 201919, 56080, 2142553, 326263, 8172117,
2304253, 4761871, 205387, 6148422, 414559, 2893305, 2158562, 465972, 304078,
1841018, 1915571]

mem = math.pow(2, 26)
br_kreni = 20
minimum = f = mem
brIter = 100000
T = 32 * 1024 * 1024



def novaTacka():
	arr = []
	for i in range(64):
		arr.append(random.randint(0, 1))

	return arr

def p(fnovo, f, T):
	p = math.exp(-1 * (fnovo - f) / T)
	if random.random() > p:
		return 0
	else:
		return 1

def racunajf(x):
	suma = 0
	for i in range(len(x)):
		if(x[i] == 1):
			suma += fajl[i]
	if(suma > mem):
		return mem
	return mem - suma


hmax = 64
hmin = 2
koef = (hmin - hmax)/brIter
def h(i):
	return int(koef * i + hmax)

def xIzmeni(x, i):
	menjam = h(i)
# 	print(menjam)
	indexi = random.sample(range(64), menjam)
	for j in indexi:
		if(x[j] == 0):
			x[j] = 1
		else:
			x[j] = 0

fmin = mem
xmin = []
x = []
matrix = []
a = 0.96
for i in range(br_kreni):
	x = novaTacka()
	f = mem
	pomLista = []
	for j in range(brIter):
		xnovo = x.copy()
		xIzmeni(xnovo, j)
		fnovo = racunajf(xnovo)
		if(fnovo < f):
			f = fnovo
			x = xnovo
			if(fnovo < fmin):
				fmin = f
				xmin = x

		else:
			if(p(fnovo, f, T) == 1):
				f = fnovo
				x = xnovo
		T *= a
		pomLista.append(f)

	pomLista = np.minimum.accumulate(pomLista)
	matrix.append(pomLista)


print(xmin)
print(fmin)


x = np.linspace(1, brIter, brIter)
for i in range(br_kreni):
	plt.loglog(x, matrix[i])

plt.xlabel("iter")
plt.ylabel("kumulativni minimumi")

plt.figure()

srednjiMinimum = []

for i in range(brIter):
	tmp = 0
	for j in range(br_kreni):
		tmp += matrix[j][i]
	srednjiMinimum.append(tmp/br_kreni)

plt.loglog(x, srednjiMinimum)
plt.xlabel("iter")
plt.ylabel("srednji kumulativni minimum")