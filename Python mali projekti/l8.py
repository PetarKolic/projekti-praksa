import numpy as np
import matplotlib.pyplot as plt
import random
import math


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
br_populacije = 2000
br_iter = 50
minimum = f = mem
n = len(fajl)

def fun(x):
	fx = 0
	for i in range(n):
		if(x[i] == 1):
			fx += fajl[i]
	
	razlika = mem - fx
	if(razlika < 0):
		return mem
	else:
		return razlika

def giveProbability(population, pUkupno):
	verovatnoce = []
	for i in range(br_populacije):
		verovatnoce.append(fun(population[i])/ pUkupno)
	return verovatnoce

def russianRoulete(population): # selekcija
	pUkupno = 0
	for i in range(br_populacije):
		pUkupno += fun(population[i])
		
	niz_verovatnoca = giveProbability(population, pUkupno)
	
	newPopulation = []

	izbor = np.random.choice(range(0, br_populacije), size =int(br_populacije/5), p= niz_verovatnoca, replace = False)
	for i in range(int(br_populacije/5)):
		newPopulation.append(population[izbor[i]])
	
	return newPopulation

def ukrstanje(population):
	i = 0
	pop_length = br_populacije / 5
	newPopulation = []
	wall = random.randrange(len(fajl))
	while i < br_populacije:
		i1 = random.randrange(pop_length)
		i2 = random.randrange(pop_length)
		if(random.random() < 0.8):
			lst = population[i1][0:wall]
			lst.extend(population[i2][wall:64])
			newPopulation.append(lst)
			i += 1
	
	return newPopulation

def mutation(population):
	for i in range(br_populacije):
		if(random.random() < 0.15):
			bit = random.randrange(64)
			if(population[i][bit] == 1):
				population[i][bit] = 0
			else:
				population[i][bit] = 1
	return population
				

def minimumFun(population):
	x = population[0]
	y = fun(population[0])
	
	for i in range(br_populacije):
		if(y > fun(population[i])):
			y = fun(population[i])
			x = population[i]
			
	return x


minX = 0
minY = 666666
matrix = []
for i in range(br_kreni):
	population = [[random.randint(0, 1) for d in range(n)] for l in range(br_populacije)]
	intermediate = []
	for i in range(br_iter):
		population = russianRoulete(population)
		population = ukrstanje(population)
		population = mutation(population)
		
		currX = min(population, key = fun)
		currY = fun(currX)
# 		print(currY)
		if(currY < minY):
			minY = currY
			minX = currX
		for a in population:
			intermediate.append(fun(a))
		
	intermediate = np.minimum.accumulate(intermediate)
	matrix.append(intermediate)

print(minX)
print("f(x) = ", minY)


total = br_iter * br_populacije
x = np.linspace(1, total, total)
for i in range(br_kreni):
	plt.loglog(x, matrix[i], label="t"+ str(i))

plt.xlabel("iter")
plt.ylabel("kumulativni minimumi")

plt.figure()

mediumMinimum = []

for i in range(total):
	tmp = 0
	for j in range(br_kreni):
		tmp += matrix[j][i]
	mediumMinimum.append(tmp/br_kreni)

plt.loglog(x, mediumMinimum)
plt.xlabel("iter")
plt.ylabel("srednji kumulativni minimum")