package main;

import model.data_structures.Colas;
import model.data_structures.Stack;
import model.data_structures.ColasPrioritarias;
import model.objects.Edificios;
import model.objects.Personas;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;

public class Main {

	private static BufferedReader br;
	private static Edificios[] buildings;
	private static Colas<Personas> queue;
	private static ColasPrioritarias<Personas> priorityQueue;
	private static Stack<Personas> stack;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		queue = new Colas<>();
		stack = new Stack<>();
		readInput();
		askOffice();
	}

	private static void readInput() throws Exception {
		buildings = new Edificios[Integer.parseInt(br.readLine())];
		for (int i = 0; i < buildings.length; i++) {
			String[] prop = br.readLine().split(" ");
			int countPerson = Integer.parseInt(prop[1]);
			priorityQueue = new ColasPrioritarias<>(countPerson, new Comparator<Personas>() {
				@Override
				public int compare(Personas o1, Personas o2) {
					return o1.getOficina() - o2.getOficina();
				}
			});
			buildings[i] = new Edificios(prop[0].charAt(0), Integer.parseInt(prop[2]), Integer.parseInt(prop[3]));
			fillQueue(countPerson);
			fillPriorityQueue(countPerson);
			fillStack(countPerson);
			assignOffice(countPerson, i);
			if (i != buildings.length - 1) {
				br.readLine();
				System.out.println("");
			}
		}
	}

	private static void fillQueue(int countPerson) throws Exception {
		for (int i = 0; i < countPerson; i++) {
			String[] person = br.readLine().split(" ");
			Personas p = new Personas(person[0], Integer.parseInt(person[1]), Integer.parseInt(person[2]));
			queue.enqueue(p);
		}
	}

	private static void fillPriorityQueue(int countPerson) throws Exception {
		for (int i = 0; i < countPerson; i++) {
			priorityQueue.offer(queue.dequeue());
		}
	}

	private static void fillStack(int countPerson) throws Exception {
		for (int i = 0; i < countPerson; i++) {
			stack.push(priorityQueue.poll());
		}
	}

	private static void assignOffice(int countPerson, int building) throws Exception {
		for (int i = 0; i < countPerson; i++) {
			if (0 < stack.top().getOficina() && stack.top().getOficina() <= buildings[building].getNum_oficinas()) {
				if (buildings[building].getoficinas().search(stack.top().getOficina()) == null) {
					try {
						buildings[building].getoficinas().insert(stack.top().getOficina(), stack.top());
						System.out.println(
								stack.top().getNombre() + " Fue asignado a la oficina: " + stack.top().getOficina());
					} catch (Exception e) {
						System.out.println(stack.top().getNombre() + " No puede ser asignado a una oficina");
					}
				} else {
					System.out.println(stack.top().getNombre() + " No puede ser asignado a una oficina");
				}
			} else {
				System.out.println(stack.top().getNombre() + " No puede ser asignado a una oficina que no existe");
			}
			stack.pop();
		}
	}

	private static void askOffice() throws IOException {
		System.out.println("\n Desea saber en que oficina se encuentra cada persona?\n1. Sisas\n2. Nonas");
		if (Integer.parseInt(br.readLine()) == 1) {
			System.out.println("\n Ingrese la letra del edifico al que desea ingresar? \n A \n B \n C \n D");
			int indexBuilding = validateBuilding(br.readLine().charAt(0));
			if (indexBuilding != -1) {
				System.out.println("Ingrese el numero de la oficina");
				int office = Integer.parseInt(br.readLine());
				if (office > 0 && office <= buildings[indexBuilding].getNum_oficinas()) {
					Personas p = buildings[indexBuilding].getoficinas().search(office);
					String msg = (p == null) ? "La oficina esta vacia" : "En la oficina esta: " + p.getNombre();
					System.out.println(msg);
				} else {
					System.out.println("La oficina no existe");
				}
			} else {
				System.out.println("El edificio no existe");
			}
			askOffice();
		}
	}

	private static int validateBuilding(char in) {
		for (int i = 0; i < buildings.length; i++) {
			if (buildings[i].getName() == in) {
				return i;
			}
		}
		return -1;
	}
}
