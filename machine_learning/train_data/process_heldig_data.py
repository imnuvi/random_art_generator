

with open("/Users/ramprakash/development/accessories/notepad/heldig_data.txt") as ff:
    #print(ff.readlines())
    lines = ff.readlines()

print("clickX,clickY,userAction,c1r,c1g,c1b,c2r,c2g,c2b,c3r,c3g,c3b,c4r,c4g,c4b")
chunk = 6
for i in range(0,len(lines), chunk):
    try:
        nlines = lines[i:i+chunk]
        click_pos = nlines[0].strip().split()
        user_choice = nlines[1].strip()
        color_1 = nlines[2].strip()
        color_2 = nlines[3].strip()
        color_3 = nlines[4].strip()
        color_4 = nlines[5].strip()
    except:
        break
    print(",".join(click_pos) + "," + user_choice  + "," + color_1 + "," + color_2 + "," + color_3 + "," +  color_4)
