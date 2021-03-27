import math
import random
import numpy as np


ax = 1
ay = 5
az = 1

bx = 3
by = 2
bz = 0

cx = 5
cy = 7
cz = 1

dx = 6
dy = 3
dz = 3




def f(x):
	a = math.sqrt((ax - x[0])** 2 + (ay - x[1])** 2 + (az - x[2])** 2)
	b = math.sqrt((bx - x[0])** 2 + (by - x[1])** 2 + (bz - x[2])** 2)
	c = math.sqrt((cx - x[3])** 2 + (cy - x[4])** 2 + (cz - x[5])** 2)
	d = math.sqrt((dx - x[3])** 2 + (dy - x[4])** 2 + (dz - x[5])** 2)
	e = math.sqrt((x[3] - x[0])** 2 + (x[4] - x[1])** 2 + (x[5] - x[2])** 2)

	return a + b + c + d + e


VMAX = 0.2 * 1
W = 0.729
C = 1.494  # c1 = c2

BROJ_JATA = 60

xmin = [0] * 6
fmin = 100

BROJ_ITERACIJA = 1000


pmin = [[fmin for x in range(6)] for y in range(BROJ_JATA)]

jato = [[random.uniform(-3, 3) for x in range(6)] for y in range(BROJ_JATA)]
v = [[0 for x in range(6)] for y in range(BROJ_JATA)]



# vn = w * vn-1 + c1*rand()*(pbest - xn-1)+ c2*rand() *(gbest - xn-1)
# xn = xn-1 + v


for k in range(BROJ_ITERACIJA):
	# brzina
	vnovo = []
	for j in range(BROJ_JATA):
		vtrtr = []
		for i in range(6):
			vtr = W * v[j][i] + C*random.random() *(pmin[j][i] - jato[j][i]) + C* random.random() * (xmin[i] - jato[j][i])
			if(vtr > VMAX):
				vtr = VMAX
			vtrtr.append(vtr)
		vnovo.append(vtrtr)

	v = vnovo.copy()

	# odredjivanje novog jata
	jatoNovo = []
	for j in range(BROJ_JATA):
		trtr = []
		for i in range(6):
			tr = jato[j][i] + v[j][i]
			trtr.append(tr)
		jatoNovo.append(trtr)
	jato = jatoNovo.copy()


	# odredjivanje lokalnih i globalnih minimuma

	for i in range(BROJ_JATA):
		ftr = f(jato[i])

		if(ftr < f(pmin[i])):
			pmin[i] = jato[i].copy()
			if(ftr < fmin):
				fmin = ftr
				xmin = jato[i].copy()



print(xmin)
print(fmin)
