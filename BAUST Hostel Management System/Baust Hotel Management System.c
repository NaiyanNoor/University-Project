#include<stdio.h>


int a, b, c, d, e, f,sf=0,ff=0;
int i,c,s=-1,g=1;
int temp,admin=4321,user=1234;
struct node
{
    int id;
    char name[50];
    int mobile_number[20];
    int seat_fee;
    int food_fee;
    int total;
    struct node *next;
    }*start,*head,*tail;

int main()
{
    welcome();
    main_menu();
}
welcome()
{

    printf("\t\t*************************************\n");
    printf("\t\t*****          Welcome         ******\n");
    printf("\t\t*****            To            ******\n");
    printf("\t\t*****          BAUST           ******\n");
    printf("\t\t***** Abbus Uddin Ahmed  Hall  ******\n");
    printf("\t\t*****      CSE 2ND Batch       ******\n");
    printf("\t\t*************************************\n");
}
main_menu()
{
    printf("\n\n\t\t*********************************************\n");
    printf("\t\t***     press 1 for Administrator         ***\n");
    printf("\t\t***     press 2 for User                  ***\n");
    printf("\t\t***     press 3 for Visitor               ***\n");
    printf("\t\t*********************************************\n\n\n");
    printf("Enter your Choice:");

    scanf("%d",&a);
    if(a == 1)
    {
        printf("Welcome Administrator\n");
        Administrator();
    }
    else if(a == 2)
    {
        printf("Welcome User\n");
        reg_user();
    }
    else if(a == 3)
    {
        printf("Welcome visitor\n");
        visitor();
    }
    else
    {
        printf("Invalid Input\n");
        printf("Try again\n");
        main_menu();
    }
}

Administrator()
{
    printf("Enter Password for log in:\n");
    printf("or\n");
    printf("Press 0 for Main Menu\n");
    printf("Enter your Choice:");
    scanf("%d",&temp);
    if(temp == admin)
    {
        admin_interface();
    }
    else if(temp == 0)
    {
        main_menu();
    }
    else
    {
        printf("Invalid Input\n");
        printf("Try again\n");
        Administrator();
    }
}

admin_interface()
{
    printf("\n\n\t\t===========================================\n");
    printf("\t\t|     Press 1 for BAUST student information|\n");
    printf("\t\t|     Press 2 for Account                  |\n");
    printf("\t\t|     Press 3 for Employee                 |\n");
    printf("\t\t|     Press 0 for Main Menu                |\n");
    printf("\t\t===========================================\n\n");
    printf("Enter your Choice: ");
    scanf("%d",&temp);
    if(temp == 1)
    {

        student_info();
        printf("\n\n");
        admin_interface();
    }
    else if(temp == 2)
    {
        printf("\t\t\t**************************************");
        printf("\n\t\t\t*   Total Seat fee : %d           *\n", sf);
        printf("\t\t\t---------------------------------------\n");
        printf("\n\t\t\t*   Total Food fee : %d           *\n", ff);
        printf("\t\t\t---------------------------------------\n");
        printf("\n\t\t\t*   Total amount is: %d           *\n", sf+ff);
        printf("\t\t\t**************************************");
        printf("\n\n");
        admin_interface();

    }
    else if(temp == 3)
    {
       printf("\n\n\t||**  Hostel in charge  **||**  Cooker  **||**  Gate keeper  **||\n");
       printf("\t------------------------------------------------------------------\n");
       printf("\t||    Naiyan Noor         ||  Akiba Ali   ||   Jolil Mia       ||\n");
       printf("\t||    Rakibul Arifin      ||  Rahim Shek  ||   Mamun Mia       ||\n");
       printf("\t||    Sami                ||  Shamim      ||   Saiful hasan    ||\n");
       printf("\t------------------------------------------------------------------\n");
       printf("\n\n");
       admin_interface();
    }
    else if(temp == 0)
    {
        main_menu();
        printf("\n\n");
    }
    else
    {
        printf("Invalid Input\n");
        admin_interface();
    }
}









student_info()
{
    printf("\n\n\t\t\tTotal number of Student is:%d",s);
    head=start->next;
    while(head!=NULL)
    {
        printf("\n\n %d\n %s\n %d\n %d\n %d\n\n ",head->id,head->name,head->mobile_number,head->seat_fee,head->food_fee);
        head=head->next;
    }
}




user_interface()
{
    printf("\n\n\t\t\t\tEnter Password for log in:\n");
    printf("\t\t\t\tor\n");
    printf("\t\t\t\tPress 0 for Main Menu\n\n");
    printf("Enter your Choice:");
    scanf("%d",&temp);
    if(temp == user)
    {
        reg_user();
    }
    else if(temp == 0)
    {
        main_menu();
    }
    else
    {
        printf("Invalid Input\n");
        printf("Try again\n");
        user_interface();
    }
}

reg_user(){
    printf("\n\n\t\t\t**********************************************\n");
    printf("\t\t\t***    press 1 for own information         ***\n");
    printf("\t\t\t***    press 2 for hostel rule regulation  ***\n");
    printf("\t\t\t***    press 3 for food menu               ***\n");
    printf("\t\t\t***    press 4 for main menu               ***\n");
    printf("\t\t\t**********************************************\n");
    printf("\n\nEnter your choice: ");
    scanf("%d",&i);
    if(i==1){
        search();
        printf("\n\n");
        reg_user();

    }
    else if(i==2){
        rulls();
        printf("\n\n");
        reg_user();
    }
    else if(i==3){
        food_menu();
        printf("\n\n");
        reg_user();
    }
    else if(i==4){
        main_menu();
        printf("\n\n");
    }
    else
    {
        printf("Invalid Input\n");
        printf("Try again\n");
        reg_user();
    }

}

search(){
    int n;
    head=start;
    printf("\n\n\t\t\t**********************************************\n");
    printf("\t\t\t***    Enter Your ID or press 0 for back   ***\n");
    printf("\t\t\t**********************************************\n");
    printf("\n\nEnter Your ID : ");
    scanf("%d",&n);

    while (head->next!=NULL)
    {
        if (head->id==n)
        {
            printf("\t\t-------------------------------------\n");
            printf("\t\t*   ID        : %d\n",head->id);
            printf("\t\t-------------------------------------\n");
            printf("\t\t*   Name      : %s\n",head->name);
            printf("\t\t-------------------------------------\n");
            printf("\t\t*   Mobile no : 01%d\n",head->mobile_number);
            printf("\t\t-------------------------------------\n");
            printf("\t\t*   Seat fee  : %d\n",head->seat_fee);
            printf("\t\t-------------------------------------\n");
            printf("\t\t*   Food fee  : %d\n",head->food_fee);
            printf("\t\t-------------------------------------\n");
            return 0;
        }
        head=head->next;
    }
    if(n==0)
    {
        reg_user();
    }
    else
    {
        printf("\n\n\t\t\tYour ID is Not Match :%d\n\t\t\t\tTRY AGAIN\n)",n);
        return search ();
    }
}








visitor()
{
    printf("press 1 for Hostel 1\n");
    printf("press 2 for Hostel 2\n");
    printf("press 3 for Hostel 3\n");
    printf("press 4 for Hostel 4\n");
    printf("press 5 for Hostel 5\n");
    printf("press 6 for Hostel 6\n");
    printf("press 7 for Sort by Distance\n");
    printf("press 8 for Sort by Cost\n");
    printf("press 9 for Sort by Quality\n");
    printf("press 11 Hostel Rules\n");
    printf("press 11 special_service\n");
    printf("Press 0 for Main Menu\n");
    printf("Enter your Choice:");

    scanf("%d",&b);
    if(b==1)
    {
        hostel_1();
    }
    else if(b==2)
    {
        hostel_2();
    }
    else if(b==3)
    {
        hostel_3();
    }
    else if(b==4)
    {
        hostel_4();
    }
    else if(b==5)
    {
        hostel_5();
    }
    else if(b==6)
    {
        hostel_6();
    }
    else if(b==7)
    {
        hostel_1();
    }
    else if(b==8)
    {
        hostel_1();
    }
    else if(b==9)
    {
        hostel_1();
    }
    else if(b==10)
    {
        rulls();
    }
    else if(b==11)
    {
        special_service();
    }
    else
    {
        printf("Invalid Input\n");
        printf("Try again\n");
        visitor();
    }
}



hostel_1()
{
    printf("welcome to Hostel 1\n");
    printf("Cost 5000\n");
    printf("Distance 5KM\n");
    printf("Quality Good\n");
    printf("Press 1 for registration\n");
    printf("Press 2 for Back\n");
    printf("Press 0 for Main Menu\n");
    printf("Enter your Choice:");
    scanf("%d",&temp);
    if(temp==1)
    {
        registration();
    }
    else if(temp==2)
    {
        visitor();
    }
    else if(temp==0)
    {
        main_menu();
    }
    else
    {
        printf("Invalid Input\n");
        printf("Try again\n");
        hostel_1();
    }
}
hostel_2()
{
    printf("welcome to Hostel 2\n");
    printf("welcome to Hostel 1\n");
    printf("Cost 5000\n");
    printf("Distance 5KM\n");
    printf("Quality Good\n");
    printf("Press 1 for registration\n");
    printf("Press 2 for Back\n");
    printf("Press 0 for Main Menu\n");
    printf("Enter your Choice:");
    scanf("%d",&temp);
    if(temp==1)
    {
        registration();
    }
    else if(temp==2)
    {
        visitor();
    }
    else if(temp==0)
    {
        main_menu();
    }
    else
    {
        printf("Invalid Input\n");
        printf("Try again\n");
        hostel_2();
    }
}
hostel_3()
{
    printf("welcome to Hostel 3\n");
    printf("welcome to Hostel 1\n");
    printf("Cost 5000\n");
    printf("Distance 5KM\n");
    printf("Quality Good\n");
    printf("Press 1 for registration\n");
    printf("Press 2 for Back\n");
    printf("Press 0 for Main Menu\n");
    printf("Enter your Choice:");
    scanf("%d",&temp);
    if(temp==1)
    {
        registration();
    }
    else if(temp==2)
    {
        visitor();
    }
    else if(temp==0)
    {
        main_menu();
    }
    else
    {
        printf("Invalid Input\n");
        printf("Try again\n");
        hostel_3();
    }
}
hostel_4()
{
    printf("welcome to Hostel 4\n");
    printf("welcome to Hostel 1\n");
    printf("Cost 5000\n");
    printf("Distance 5KM\n");
    printf("Quality Good\n");
    printf("Press 1 for registration\n");
    printf("Press 2 for Back\n");
    printf("Press 0 for Main Menu\n");
    printf("Enter your Choice:");
    scanf("%d",&temp);
    if(temp==1)
    {
        registration();
    }
    else if(temp==2)
    {
        visitor();
    }
    else if(temp==0)
    {
        main_menu();
    }
    else
    {
        printf("Invalid Input\n");
        printf("Try again\n");
        hostel_4();
    }
}
hostel_5()
{
    printf("welcome to Hostel 5\n");
    printf("welcome to Hostel 1\n");
    printf("Cost 5000\n");
    printf("Distance 5KM\n");
    printf("Quality Good\n");
    printf("Press 1 for registration\n");
    printf("Press 2 for Back\n");
    printf("Press 0 for Main Menu\n");
    printf("Enter your Choice:");
    scanf("%d",&temp);
    if(temp==1)
    {
        registration();
    }
    else if(temp==2)
    {
        visitor();
    }
    else if(temp==0)
    {
        main_menu();
    }
    else
    {
        printf("Invalid Input\n");
        printf("Try again\n");
        hostel_5();
    }
}
hostel_6()
{
    printf("welcome to Hostel 6\n");
    printf("welcome to Hostel 1\n");
    printf("Cost 5000\n");
    printf("Distance 5KM\n");
    printf("Quality Good\n");
    printf("Press 1 for registration\n");
    printf("Press 2 for Back\n");
    printf("Press 0 for Main Menu\n");
    printf("Enter your Choice:");
    scanf("%d",&temp);
    if(temp==1)
    {
        registration();
    }
    else if(temp==2)
    {
        visitor();
    }
    else if(temp==0)
    {
        main_menu();
    }
    else
    {
        printf("Invalid Input\n");
        printf("Try again\n");
        hostel_6();
    }
}



registration()
{
    printf(" \n\n\t\t\t:-) ;-) Create your Account !!!!\n\n");
    printf(" \n\n\t\t\t:-) ;-) Create your Account !!!!\n\n");
    printf(" \n\n\t\t\t:-) ;-) Create your Account !!!!\n\n");
    printf("\n\n");
    if(g==1)
    {
        start=(struct node*)malloc(sizeof(struct node));
        printf("Enter the id: ");
        scanf("%d",&start->id);
        printf("Enter your name: ");
        scanf("%s",&start->name);
        printf("Enter your mobile number:  ");
        scanf("%d",&start->mobile_number);
        printf("Enter seat fee: ");
        scanf("%d",&start->seat_fee);
        printf("Enter food fee: ");
        scanf("%d",&start->food_fee);
        start->next=NULL;
        head=start;
    }
    else
    {
        tail=(struct node*)malloc(sizeof(struct node));
        printf("Enter the id: ");
        scanf("%d",&tail->id);
        printf("Enter your name: ");
        scanf("%s",&tail->name);
        printf("Enter your mobile number:  ");
        scanf("%d",&tail->mobile_number);
        printf("Enter seat fee: ");
        scanf("%d",&tail->seat_fee);
        printf("Enter food fee: ");
        scanf("%d",&tail->food_fee);
        tail->next=NULL;
        head->next=tail;
        head=tail;
        sf=sf+tail->seat_fee;
        ff=ff+tail->food_fee;
    }
    g=g+1;
    s=s+1;
    confirm();
}

 special_service()
{
    printf("\n\n");
    printf("\t------------------------------------\n\t| Abbus Uddin Ahmed hostle two|\n\t------------------------------------  \n 1. single room with attached bathroom and balcony.\n 2. room service\n 3. cost : 5000tk only.\n\n");
    printf("\t------------------------------------\n\t| Abbus Uddin Ahmed hostle one  |\n\t------------------------------------  \n 1. single room with attached bathroom and balcony.\n 2. cost : 4000tk only.\n\n");
    printf("\t------------------------------------\n\t| Abbus Uddin Ahmed hostle three|\n\t------------------------------------  \n 1. single room with attached bathroom and balcony.\n 2. cost : 4000tk only.\n\n");
}


rulls()
{
    printf("\n\t1.you have to pay hostel charge every month within first 10 days.\n  ");
    printf("\tafter 10 day you have to pay 20tk for each day & 200tk for each month.\n");
    printf("\t2.you have to back in hostel in due time. \n\t\t\tsummer: last time 7.00pm\n\t\t\twinter: last time 6.00pm \n");
    printf("\t3.before 6 month you cant leave the hostel.\n");
    printf("\t4.if you want to leave this hostel you have to inform the hostel \n\tauthority before 2 month.\n");
    printf("\t5.if you have computer or laptop, we have to pay 100tk as fee.\n");
    printf("\t6.only your two local guardian can come to meet with you\n");
}



 food_menu(){
        printf("\n\n");
    printf("| Day |     Breakfast    |        lunch        |            Dinner          |\n");
    printf("=============================================================================\n");
    printf("| sat |   ruti,vegetable | rice,chicken,lentil | rice,vegetable,vorta,lentil|\n");
    printf("-----------------------------------------------------------------------------\n");
    printf("| sun |  rice, vegetable | rice,fish,lentil    |  rice,vegetable,egg,lentil |\n");
    printf("-----------------------------------------------------------------------------\n");
    printf("| mon |     ruti,egg     | rice,chicken,lentil |  rice,fish,lentil          |\n");
    printf("-----------------------------------------------------------------------------\n");
    printf("| tue |  khichuri,egg    | rice,meat,lentil    |  rice,fish,lentil          |\n");
    printf("-----------------------------------------------------------------------------\n");
    printf("| wed |   rice, vorta    | rice,chicken,lentil |  rice,egg,lentil           |\n");
    printf("-----------------------------------------------------------------------------\n");
    printf("| thu |  ruti, vegetable | rice,fish,lentil    |  rice,vegetable,egg,lentil |\n");
    printf("-----------------------------------------------------------------------------\n");
    printf("| fri |    khichuri,egg  | rice,chicken,lentil |  rice,fish,lentil          |\n");
    printf("=============================================================================\n");
}

confirm()
{
    printf(" \n\n\t\t\t:-) ;-) Congratulation !!!!\n\n");
    printf(" \n\n\t\t\t:-) ;-) Your Account is Create !!!!\n\n");
    printf("\t\t  You become a member of our Hostel.\n");
    main_menu();
}
