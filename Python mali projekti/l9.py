import math
import random as random

S=(2.424595205726587e-01, 1.737226395065819e-01, 1.315612759386036e-01,
 1.022985539042393e-01, 7.905975891960761e-02, 5.717509542148174e-02,
 3.155886625106896e-02, -6.242228581847679e-03, -6.565183775481365e-02,
 -8.482380513926287e-02, -1.828677714588237e-02, 3.632382803076845e-02,
 7.654845872485493e-02, 1.152250132891757e-01, 1.631742367154961e-01,
 2.358469152696193e-01, 3.650430801728451e-01, 5.816044173713664e-01,
 5.827732223753571e-01, 3.686942505423780e-01)

F = 0.9
CR = 0.8
N = 20
R0 = 15

def fillCord():

	cord = []
	for i in range(N):
		y = R0 * math.sin(2 * math.pi * i/N)
		x = R0 * math.cos(2 * math.pi * i/N)
		lst = [x, y]
		cord.append(lst)

	return cord

coordinates = fillCord()
def f(x):
	total = 0
	l = 0
	r = 0

	for i in range(N):

		l = x[4] / (math.sqrt((coordinates[i][0] - x[0])**2 + (coordinates[i][1] - x[1])**2))
		r = x[5] / (math.sqrt((coordinates[i][0] - x[2])**2 + (coordinates[i][1] - x[3])**2))
		total = total + math.pow((l + r - S[i]), 2)

		if(math.sqrt(x[0] **2 + x[1] **2) > R0):
			return 100
		if(math.sqrt(x[2] **2+ x[3] **2) > R0):
			return 100

	return total

k = 0
MAX_ITERATIONS = 3000
VECTOR_LEN = 6
POPULATION_NUM = 60

population =  [[random.uniform(-15, 15) for x in range(VECTOR_LEN)] for y in range(POPULATION_NUM)]

fsearch = math.pow(10, -14)
fmin = 100
minimum = []
while k < MAX_ITERATIONS and fmin > fsearch:

	xabc = [[random.randrange(POPULATION_NUM) for x in range(3)] for y in range(POPULATION_NUM)]

	z = []
	for i in range(POPULATION_NUM):
		tmp = []
		for j in range(VECTOR_LEN):
			tmptmp = population[xabc[i][0]][j] + F * (population[xabc[i][1]][j] - population[xabc[i][2]][j])
			tmp.append(tmptmp)

		z.append(tmp.copy())

	R = random.randrange(VECTOR_LEN)
	tmp = []
	population_new = []
	for i in range(POPULATION_NUM):
		tmp = []
		for j in range(VECTOR_LEN):
			if((random.random() < CR) or (k == R)):
				tmp.append(z[i][j])
			else:
				tmp.append(population[i][j])

		if(f(tmp) < f(population[i])):
			population_new.append(tmp.copy())
		else:
			population_new.append(population[i].copy())

	population = population_new

	xtrMin = min(population, key = f)

	if(f(xtrMin) < fmin):
		fmin = f(xtrMin)
		minimum = xtrMin.copy()

	k = k + 1

print("Minimal vector", minimum)
print("Minimal f", fmin)

