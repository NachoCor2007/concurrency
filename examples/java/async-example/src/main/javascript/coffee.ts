
let coffeeNumber = 0;

function makeCoffeePromise(): Promise<string> {
    return new Promise((resolve) => {
        console.log("Making Coffee");
        setTimeout(() => {
            coffeeNumber += 1;
            resolve(`coffee ${coffeeNumber}`);
        }, 2000);
    });
}

function drink(coffee: string) {
    console.log(`Drinking ${coffee}`);
}

function chatWithColleagues() {
    console.log("Chatting with colleagues");
}

function coffeeBreak() {
    const f: Promise<string> = makeCoffeePromise();
    f.then(coffee => {
        drink(coffee);
    })
    chatWithColleagues();
}

async function futureCoffeeBreak2(): Promise<void> {
        const f: Promise<string> = makeCoffeePromise();
        chatWithColleagues();
        const coffee = await f;
        drink(coffee);
}

for (let i = 0; i < 3; i++) {
    coffeeBreak();
}