
package pl.sda;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Debitor debitor1 = new Debitor();
        RulesEngine rulesOfLoan1 = new RulesEngine(new BigDecimal("0.06"), new BigDecimal("3000"));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj swój wiek: ");
        debitor1.setAge(scanner.nextInt());
        System.out.print("Podaj swój dochód: ");
        debitor1.setIncome(scanner.nextBigDecimal());
        scanner.nextLine(); //potrzebne, jeśli wczytujemy napis po wczytaniu liczby
        System.out.print("Podaj wysokość rat aktualnie spłacanych kredytów: ");
        String loanInstallmentsInput = scanner.nextLine();
        String[] loanInstallmentsString = loanInstallmentsInput.split(",");
        BigDecimal[] loanInstallments = new BigDecimal[loanInstallmentsString.length];
        BigDecimal sumOfLoanInstallments = new BigDecimal("0");
        for (int i = 0; i < loanInstallmentsString.length; i++) {
            BigDecimal loanInstallment = new BigDecimal(loanInstallmentsString[i]);
            loanInstallments[i] = loanInstallment;
            sumOfLoanInstallments = sumOfLoanInstallments.add(loanInstallment);
        }

        debitor1.setLoanInstallments(loanInstallments); //TODO: inaczej
        debitor1.setSumOfLoanInstallments(sumOfLoanInstallments);

        String isDelayedInput;
        do {
            System.out.print("Czy do tej pory wystąpiły jakieś opóźnienia w spłacie rat kredytów? [t=TAK/n=NIE]");
            isDelayedInput = scanner.nextLine();
        } while (!isDelayedInput.matches("[TtNn]"));

        debitor1.setRepaymentDelayed(isDelayedInput.matches("[Tt]"));

        System.out.print("Podaj wysokość kredytu o jaki wnioskujesz: ");
        debitor1.setAmountOfCredit(scanner.nextBigDecimal());
        System.out.print("Okres spłaty wnioskowanego kredytu (w latach): ");
        debitor1.setRepaymentPeriod(scanner.nextInt());

        System.out.println(rulesOfLoan1.isCreditApproved(debitor1));

        // Napisz aplikacje do analizy zdolnosci kredytowej, parametry które powinny zostać wzięte pod uwagę to:
        // * wiek
        // * dochód
        // * sumarycznie raty kredytów, które kredytobiorca spłaca
        // * czy było opóźnienie w spłacie kredytu - jeśli było to odrzuć
        // * kwota kredytu
        // * koszt kredytu
        // * okres spłaty (w latach)
        // * rata kredytu, który kredytobiorca chce wziąć
        // Reguły odrzucania:
        // a wiek > 65 odrzuć
        // b sumaryczne raty kredytów + rata nowego kredytu >= dochód * 0,80 odrzuć
        // c opóźnienie w spłacie == tak odrzuć
        // d wiek + okres spłaty > 65 odrzuć
        // jak wyliczyć koszt kredytu
        // kwota kredytu * oprocentowanie + prowizja
        // Jak wyliczyć ratę kredytu
        // kwota kredytu + koszt kredytu / okres spłaty
        // ************ Jak powinna działać aplikacja? ***************
        // 1. Klient jest pytany o:
        // * wiek (age)
        // * dochód (income)
        // * raty kredytów które spłaca (wprowadza po przecinku) (loanInstallments)
        // * odpowiada czy było opóźnienie (repaymentDelayed)
        // * wprowadza ile chce pożyczyć (amountOfCredit)
        // * wprowadza na jak dlugo chce pozyczyc
        // Wprowadzone dane zapisz do klasy Debtor (raty kredytów wprowadzone po przecinku musisz rozbić na pojedyncze elementy, zapisz je tablicy)
        // 2. Stwórz klasę RulesEngine, która będzie przetwarzała podane argumenty, powinna:
        // a mieć pola, które są wypełniane w konstruktorze:
        // * oprocentowanie (np. 0,06)
        // * kwota stała prowizji (np. 3000)
        // b w RulesEngine utwórz metodę isCreditApproved przyjmującą obiekt typu Debtor - dane osoby wnioskującej o kredyt i zwracającą boolean (true = wniosek ok, false = wniosek odrzucony)
        // * w metodzie zaimplementuj wszystkie reguły odrzucania, jeśl żadna z nich nie jest spełniona zwróć true, jeśli którakolwiek jest spełniona zwróć false
        // 3. W main utwórz zmienną zawierającą nowy obiekt typu RulesEngine podając w konstruktorze parametry (np. 0,06 i 3000), przetestuj czy rzeczywiscie podane reguły działają
        // 4. W argumencie wprowadź obiekt Debtor utworzony z wprowadzonych przez klienta danych
    }

}

